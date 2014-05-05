/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2014 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.server.rule2.ws;

import org.sonar.api.server.ws.Request;
import org.sonar.api.server.ws.RequestHandler;
import org.sonar.api.server.ws.Response;
import org.sonar.api.server.ws.WebService;
import org.sonar.api.utils.text.JsonWriter;
import org.sonar.server.rule2.RuleService;

import java.util.List;

public class TagsAction implements RequestHandler {

  private final RuleService service;

  public TagsAction(RuleService service) {
    this.service = service;
  }

  void define(WebService.NewController controller) {
    controller
      .createAction("tags")
      .setDescription("Search for a collection of relevant rules matching a specified query")
      .setSince("4.4")
      .setHandler(this);
  }

  @Override
  public void handle(Request request, Response response) {
    List<String> tags = service.listTags();
    JsonWriter json = response.newJsonWriter().beginObject();
    json.name("tags").beginArray();
    for (String tag : tags) {
      json.value(tag);
    }
    json.endArray().endObject().close();
  }
}
